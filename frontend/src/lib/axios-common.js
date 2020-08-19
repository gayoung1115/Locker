import axios from "axios";

export default axios.create({
  // baseURL: "http://i3a606.p.ssafy.io:8000/api",
  // baseURL: "https://i3a606.p.ssafy.io/api",
  //baseURL: "http://i3a606.p.ssafy.io:8000/api",
  baseURL: "http://localhost:8000/api",

  headers: {
    "Content-type": "application/json",
  },
});
