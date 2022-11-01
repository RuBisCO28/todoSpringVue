import { createWebHistory, createRouter } from "vue-router";
import { RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    alias: "/todos",
    name: "todos",
    component: () => import("./components/TodoList.vue"),
  },
  {
    path: "/todos/:id",
    name: "todo-details",
    component: () => import("./components/TodoDetails.vue"),
  },
  {
    path: "/register",
    name: "register",
    component: () => import("./components/RegisterTodo.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
