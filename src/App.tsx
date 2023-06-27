import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Footer } from './components/Footer';
import { CadastrarUsuario } from './components/CadastrarUsuario';
import { Login } from './components/Login';
import { HomePage } from './app/HomePage';
import { Principal } from './app/Principal';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { CadastroPlano } from './components/CadastroPlano';
import { UserProvider } from './contexts/providers/UserProvider';

export function App() {
  
  return (
    <UserProvider>
    <Router>
       <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/cadastro" element={<CadastrarUsuario />} />
        <Route path="/plano" element={<CadastroPlano />} />
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={<Principal />} />
      </Routes>
      <Footer />
      <ToastContainer />
    </Router>
    </UserProvider>
  );
}


