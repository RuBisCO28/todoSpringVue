<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input v-model="title" type="text" class="form-control" placeholder="Search by title" />
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button" @click="searchTodos">Search</button>
        </div>
      </div>
    </div>
    <div class="col-md-8">
      <label for="status">Status</label>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault0" value="0" v-model="status">
        <label class="form-check-label" for="inlineRadio0">未着手</label>
        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" value="1" v-model="status">
        <label class="form-check-label" for="inlineRadio1">着手中</label>
        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value="2" v-model="status">
        <label class="form-check-label" for="inlineRadio2">完了</label>
      </div>
    </div>
    <div class="col-md-8">
      <label for="from">From: </label>
      <input type="date" v-model="from" :min="new Date().toISOString().split('T')[0]" onkeydown="return false">
      <label for="to">To: </label>
      <input type="date" v-model="to" :min="new Date().toISOString().split('T')[0]" onkeydown="return false">
    </div>
    <div class="col-md-6">
      <h4>Todos List</h4>
      <ul class="list-group">
        <li
          v-for="(todo, index) in todos"
          :key="index"
          class="list-group-item"
          :class="{ active: index == currentIndex }"
          @click="setActiveTodo(todo, index)"
        >
          {{ todo.title }}
        </li>
      </ul>
    </div>
    <div class="col-md-6">
      <div v-if="currentTodo.id">
        <h4>Todo</h4>
        <div>
          <label><strong>Title:</strong></label> {{ currentTodo.title }}
        </div>
        <div>
          <label><strong>Status:</strong></label>
          {{ currentTodo.status }}
        </div>
        <div>
          <label><strong>Deadline:</strong></label>
          {{ currentTodo.deadline }}
        </div>
        <div>
          <label><strong>CreatedAt:</strong></label>
          {{ currentTodo.createdAt }}
        </div>
        <div>
          <label><strong>UpdatedAt:</strong></label>
          {{ currentTodo.updatedAt }}
        </div>

        <router-link :to="'/todos/' + currentTodo.id" class="m-3 btn btn-sm btn-primary"> Edit </router-link>
      </div>
      <div v-else>
        <br />
        <p>No Todo</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import { defineComponent } from "vue";
  import TodoDataService from "../services/TodoDataService";
  import Todo from "../types/Todo";
  import ResponseData from "../types/ResponseData";
  export default defineComponent({
    name: "TodosList",
    data() {
      return {
        todos: [] as Todo[],
        currentTodo: {} as Todo,
        currentIndex: -1,
        title: "",
        status: "",
        from: "",
        to: "",
      };
    },
    mounted() {
      this.retrieveTodos();
    },
    methods: {
      retrieveTodos() {
        TodoDataService.getAll()
          .then((response: ResponseData) => {
            this.todos = response.data;
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      },
      setActiveTodo(todo: Todo, index = -1) {
        this.currentTodo = todo;
        this.currentIndex = index;
      },
      searchTodos() {
          TodoDataService.search(this.title, this.status, this.from, this.to)
            .then((response: ResponseData) => {
              this.todos = response.data;
              this.setActiveTodo({} as Todo);
              console.log(response.data);
            })
            .catch((e: Error) => {
              console.log(e);
            });
      }
    }
  });
</script>

<style>
  .list {
    text-align: left;
    max-width: 750px;
    margin: auto;
  }
</style>
