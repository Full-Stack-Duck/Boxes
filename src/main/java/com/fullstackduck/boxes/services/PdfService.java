package com.fullstackduck.boxes.services;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.repositories.OrcamentoRepository;

@Service //Registro de componente
public class PdfService {

	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	@Autowired
	private OrcamentoService orcamentoService;
	
	public byte[] generatePDF(Long id) {
		
		Orcamento obj = orcamentoRepository.getReferenceById(id);
	    final int ROWS_PER_PAGE = 25;
	    try (PDDocument document = new PDDocument()) {
	        PDPage page = new PDPage(PDRectangle.A4);
	        document.addPage(page);

	        PDPageContentStream contentStream = new PDPageContentStream(document, page);

	        contentStream.addRect(20, 725, 555, 100);
	        contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.newLineAtOffset(30, 800);
            
            //Dados do usuario
            contentStream.newLineAtOffset(0, -10);
            contentStream.showText(obj.getUsuario().getNome().toUpperCase());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -15);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.showText("EMAIL: " + obj.getUsuario().getEmail());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("TELEFONE: " + obj.getUsuario().getTelefone());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("ENDEREÇO: " + obj.getUsuario().getEndereco());
            contentStream.newLine();
            contentStream.endText();

            // Dados do cliente
            contentStream.addRect(20, 625, 555, 100);
            contentStream.beginText();
            contentStream.newLineAtOffset(30, 700);
            contentStream.showText("CLIENTE N°: " + obj.getCliente().getId());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("NOME: " + obj.getCliente().getNome());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("EMAIL: " + obj.getCliente().getEmail());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("TELEFONE: " + obj.getCliente().getTelefone());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("ENDEREÇO: " + obj.getEndereco());
            contentStream.newLine();
            contentStream.endText();

            // Dados do orcamento ou pedido
            contentStream.beginText();
            contentStream.newLineAtOffset(355, 700);
            contentStream.showText("N° ORCAMENTO: " + obj.getId());
            contentStream.newLineAtOffset(0, -15);
            contentStream.newLine();
            contentStream.showText("DATA: " + formatarData(obj.getDataOrcamento()));
            contentStream.newLineAtOffset(0, -15);
            contentStream.newLine();
            contentStream.showText("ENTREGA: " + formatarData(obj.getDataEntrega()));
            contentStream.newLineAtOffset(0, -15);
            contentStream.newLine();
            contentStream.showText("TIPO DE ENTREGA: " + obj.getTipoEntrega());
            contentStream.newLineAtOffset(0, -15);
            contentStream.newLine();
            contentStream.showText("FORMA DE PAGAMENTO: " + obj.getFormaPagamento());
            contentStream.newLineAtOffset(0, -15);
            contentStream.newLine();
            contentStream.endText();
            
	        float margin = 20;
	        float yStart = page.getMediaBox().getHeight() - (margin * 13);
	        float yPosition = yStart;
	        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
	        float yEnd = margin;
	        int rowsPerPage = ROWS_PER_PAGE;
	        int rows = 0;

	        contentStream.beginText();
            contentStream.newLineAtOffset(30, 595);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.showText("ITENS DO PEDIDO");
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.endText();
            
	        Set<ItensOrcamento> itens = obj.getItens();

	        
	        drawTableHeader(contentStream, margin, yStart, tableWidth);
	        yPosition -= 20;

	        for (ItensOrcamento item : itens) {
	            if (rows >= rowsPerPage) {
	                contentStream.close();

	                page = new PDPage(PDRectangle.A4);
	                document.addPage(page);
	                contentStream = new PDPageContentStream(document, page);
	                rowsPerPage = 34;
	                
	                yStart = page.getMediaBox().getHeight() - (margin * 1);
	                drawTableHeader(contentStream, margin, yStart, tableWidth);
	                yPosition = yStart;
	                yPosition -= 20;
	                rows = 0;
	            }
	            
	            drawTableRow(contentStream, margin, yPosition, tableWidth, item);
	            //contentStream.endText();
	            yPosition -= 20;
	            rows++;
	        }

	        float tableBottomY = yPosition;
	        Double subtotal = orcamentoService.calcularSubTotal(obj); // Função para calcular o subtotal dos itens
	        Double descontoFinal = obj.getDesconto(); // Obtenha o desconto final do objeto Orcamento
	        Double taxaEntrega = obj.getTaxaEntrega(); // Obtenha a taxa de entrega do objeto Orcamento
	        double totalGeral = subtotal - descontoFinal + taxaEntrega;

	        // Posição vertical para imprimir as informações finais
	        float finalInfoY = tableBottomY - 20;

	        // Imprimir total dos itens
	        contentStream.beginText();
	        contentStream.setFont(PDType1Font.HELVETICA, 12);
	        contentStream.newLineAtOffset(margin + 10, finalInfoY);
	        contentStream.showText("Total dos Itens: R$ " + formatarValor(subtotal));
	        contentStream.endText();

	        // Imprimir desconto final
	        contentStream.beginText();
	        contentStream.setFont(PDType1Font.HELVETICA, 12);
	        contentStream.newLineAtOffset(margin + 10, finalInfoY - 15);
	        contentStream.showText("Desconto Final: R$ " + formatarValor(descontoFinal));
	        contentStream.endText();

	        // Imprimir taxa de entrega
	        contentStream.beginText();
	        contentStream.setFont(PDType1Font.HELVETICA, 12);
	        contentStream.newLineAtOffset(margin + 10, finalInfoY - 30);
	        contentStream.showText("Taxa de Entrega: R$ " + formatarValor(taxaEntrega));
	        contentStream.endText();

	        // Imprimir total geral
	        contentStream.beginText();
	        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	        contentStream.newLineAtOffset(margin + 10, finalInfoY - 45);
	        contentStream.showText("Total Geral: R$ " + formatarValor(totalGeral));
	        contentStream.endText();

	        // Obtenha as dimensões da página
	        PDRectangle mediaBox = page.getMediaBox();
	        float pageWidth = mediaBox.getWidth();

	        // Defina a fonte e o tamanho do texto
	        contentStream.setFont(PDType1Font.HELVETICA, 10);

	        // Calcule as coordenadas para o texto no centro horizontal da página
	        float textWidth = PDType1Font.HELVETICA.getStringWidth("Documento sem valor fiscal gerado por Boxes - Sistema de Gestão Empresarial") / 1000f * 10;
	        float centerX = (pageWidth - textWidth) / 2f;

	        // Posicione o texto no centro da página
	        contentStream.beginText();
	        contentStream.newLineAtOffset(centerX, finalInfoY - 75);
	        contentStream.showText("Documento sem valor fiscal gerado por Boxes - Sistema de Gestão Empresarial");
            contentStream.endText();
	        
	        contentStream.close();

	        //document.save("C:\\Users\\vinic\\OneDrive\\Área de Trabalho\\PDFS gerados\\" + obj.getCliente().getNome() + " " + obj.getId() + ".pdf");
	        var byteArray = new ByteArrayOutputStream();
	        document.save(byteArray);
	        document.close();
	        return byteArray.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	private void drawTableHeader(PDPageContentStream contentStream, float x, float y, float tableWidth) throws IOException {
	    float cellWidth = tableWidth / 6f;
	    float tableHeight = 20f;

	    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	    contentStream.setLineWidth(0.5f);
	    contentStream.setStrokingColor(Color.BLACK);

	    float textY = y - 15;

	    // Desenha as linhas verticais alinhadas com o texto do cabeçalho
	    contentStream.moveTo(x, y);
	    contentStream.lineTo(x, y - tableHeight);
	    contentStream.stroke();

	    float textX = x;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText("Cod.");
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    textX += cellWidth / 2;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText("Qtd.");
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    textX += cellWidth / 2;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText("Descrição");
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    textX += 3 * cellWidth;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText("R$ Unit.");
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    textX += cellWidth / 1.5f;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText("Desc.");
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    textX += cellWidth / 1.5f;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText("R$ Total");
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();
	    
	    textX += cellWidth / 1.5f;
	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    // Desenha as linhas horizontais
	    contentStream.moveTo(x, y);
	    contentStream.lineTo(x + tableWidth, y);
	    contentStream.stroke();

	    contentStream.moveTo(x, y - 20);
	    contentStream.lineTo(x + tableWidth, y - 20);
	    contentStream.stroke();
	}


	private void drawTableRow(PDPageContentStream contentStream, float x, float y, float tableWidth, ItensOrcamento item) throws IOException {
	    float cellWidth = tableWidth / 6f;
	    float tableHeight = 20f;

	    contentStream.setFont(PDType1Font.HELVETICA, 12);
	    contentStream.setLineWidth(0.5f);
	    contentStream.setStrokingColor(Color.BLACK);

	    float textY = y - 15;

	    float textX = x;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText(String.valueOf(item.getProduto().getId()));
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    textX += cellWidth / 2;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText(String.valueOf(item.getQuantidade()));
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    textX += cellWidth / 2;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText(String.valueOf(item.getProduto().getNome()));
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    textX += 3 * cellWidth;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText(formatarValor(item.getPrecoUnit()));
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    textX += cellWidth / 1.5f;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText(formatarValor(item.getDesconto()));
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();

	    textX += cellWidth / 1.5f;
	    contentStream.beginText();
	    contentStream.newLineAtOffset(textX + 5, textY);
	    contentStream.showText(formatarValor(item.getPrecoTotal()));
	    contentStream.endText();

	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();
	    
	    textX += cellWidth / 1.5f;
	    contentStream.moveTo(textX, y);
	    contentStream.lineTo(textX, y - tableHeight);
	    contentStream.stroke();
	    
	    contentStream.moveTo(x, y - tableHeight);
	    contentStream.lineTo(x + tableWidth, y - tableHeight);
	    contentStream.stroke();
	}

	public String formatarValor(double valor) {
	    DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
	    symbols.setDecimalSeparator(',');
	    symbols.setGroupingSeparator('.');
	    DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
	    return df.format(valor);
	}
    
    public String formatarData(Instant data) {
    	LocalDate localDate = data.atZone(ZoneId.of("America/Sao_Paulo")).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = localDate.format(formatter);
        return dataFormatada;
    }
    
    
}
