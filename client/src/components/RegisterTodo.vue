<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="title">Title</label>
        <input id="title" v-model="todo.title" type="text" class="form-control" required name="title" />
      </div>
      <div class="form-group">
        <label for="status">Status</label>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault0" value="0" v-model="todo.status">
          <label class="form-check-label" for="inlineRadio0">未着手</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" value="1" v-model="todo.status">
          <label class="form-check-label" for="inlineRadio1">着手中</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value="2" v-model="todo.status">
          <label class="form-check-label" for="inlineRadio2">完了</label>
        </div>
      </div>
      <div class="form-group">
        <label for="from">deadline</label>
        <input type="date" v-model="todo.deadline" :min="new Date().toISOString().split('T')[0]" onkeydown="return false">
      </div>
      <button class="btn btn-success" @click="saveTodo">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newTodo">Register</button>
    </div>
  </div>
</template>

<script lang="ts">
  import { defineComponent } from "vue";
  import TodoDataService from "../services/TodoDataService";
  import Todo from "../types/Todo";
  import ResponseData from "../types/ResponseData";
  export default defineComponent({
    name: "register-todo",
    data() {
      return {
        todo: {
          id: 0,
          title: "",
          status: 0,
          deadline: "",
          createdAt: "",
          updatedAt: ""
        } as Todo,
        submitted: false
      };
    },
    methods: {
      saveTodo() {
        let data = {
          title: this.todo.title,
          status: this.todo.status,
          deadline: `${this.todo.deadline}T00:00:00`,
        };
        TodoDataService.create(data)
          .then((response: ResponseData) => {
            this.todo.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch((e: Error) => {
            console.log(e);
          });
      },
      newTodo() {
        this.submitted = false;
        this.todo = {} as Todo;
      }
    }
  });
</script>

<style>
  .submit-form {
    max-width: 300px;
    margin: auto;
  }
</style>
