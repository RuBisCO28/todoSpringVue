import http from "../http-common";

/* eslint-disable */
class TodoDataService {
  getAll(): Promise<any> {
    return http.get("/todos");
  }

  get(id: any): Promise<any> {
    return http.get(`/todos/${id}`);
  }

  create(data: any): Promise<any> {
    return http.post("/todos", data);
  }

  update(id: any, data: any): Promise<any> {
    return http.put(`/todos/${id}`, data);
  }

  delete(id: any): Promise<any> {
    return http.delete(`/todos/${id}`);
  }

  search(title: string, status: string, from: string, to: string): Promise<any> {
    let requestParams = "";
    if (title !== "") {
      requestParams += `title=${title}`;
    }
    if (status !== "") {
      requestParams += `status=${status}`;
    }
    if (from !== "") {
      requestParams += `from=${from}`;
    }
    if (to !== "") {
      requestParams += `to=${to}`;
    }
    return http.get(`/todos/search?${requestParams}`);
  }
}

export default new TodoDataService();
