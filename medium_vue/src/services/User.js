import axios from "axios";

const API_URL = "http:localhost:8080/api"
class UserService {
    getUsers() {
        return axios.get(API_URL + "/userList");
    }
}
