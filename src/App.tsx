import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Header } from './components/Header';
import { Navbar } from './components/Navbar';
import { FooterNav } from './components/FooterNav';
import { Footer } from './components/Footer';
import { CadastrarUsuario } from './components/CadastrarUsuario';
import { Login } from './components/Login';
import { HomePage } from './components/HomePage';
import { Principal } from './components/Principal';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export function App() {
  return (
    <Router>
       <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/cadastro" element={<CadastrarUsuario />} />
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={<Principal />} />
      </Routes>
      <Footer />
      <ToastContainer />
      
    </Router>
  );
}


