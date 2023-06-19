import axios from "axios";
import Cookies from 'js-cookie';


export const api = axios.create({baseURL: 'http://localhost:8080', withCredentials:true})
console.log(api)


// Cookies.set('jwtToken', token);
export const token = () => Cookies.get('jwtToken');
