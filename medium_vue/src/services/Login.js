import axios from "axios";

const API_URL = "http://localhost:8080/api";

class LoginService {
  getRegister(data) {
    return axios.post(API_URL + "/register", data);
  }
  postLogin() {
    return axios.post(API_URL + "/login");
  }
}

export default new LoginService();
