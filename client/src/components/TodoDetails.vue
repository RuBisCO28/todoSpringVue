<template>
  <div v-if="currentTodo.id" class="edit-form">
    <h4>Todo</h4>
    <form>
      <div class="form-group">
        <label for="title">Title</label>
        <input id="title" v-model="currentTodo.title" type="text" class="form-control" />
      </div>
      <div class="form-group">
        <label for="status">Status</label>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault0" value="0" v-model="currentTodo.status">
          <label class="form-check-label" for="inlineRadio0">未着手</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" value="1" v-model="currentTodo.status">
          <label class="form-check-label" for="inlineRadio1">着手中</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value="2" v-model="currentTodo.status">
          <label class="form-check-label" for="inlineRadio2">完了</label>
        </div>
      </div>
      <div class="form-group">
        <label for="from">deadline</label>
        <input type="date" v-model="currentTodo.deadline" :min="currentTodo.deadline.split('T')[0]" onkeydown="return false">
      </div>
    </form>
    <button type="submit" class="mr-2 btn btn-sm btn-success" @click="updateTodo">Update</button>
    <button class="m-3 btn btn-sm btn-danger" @click="deleteTodo">Delete</button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Todo...</p>
  </div>
</template>

<script lang="ts">
  import { defineComponent } from "vue";
  import TodoDataService from "../services/TodoDataService";
  import Todo from "../types/Todo";
  import ResponseData from "../types/ResponseData";
  export default defineComponent({
    name: "TodoDetail",
    data() {
      return {
        currentTodo: {} as Todo,
        message: ""
      };
    },
    mounted() {
      this.message = "";
      this.getTodo(this.$route.params.id);
    },
    methods: {
      getTodo(id: any) {
        TodoDataService.get(id)
          .then((response: ResponseData) => {
            this.currentTodo = response.data;
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      },
      updateTodo() {
        if (!this.currentTodo.deadline.includes('T00:00:00')) {
          this.currentTodo.deadline = `${this.currentTodo.deadline}T00:00:00`;
        }
        TodoDataService.update(this.currentTodo.id, this.currentTodo)
          .then((response: ResponseData) => {
            console.log(response.data);
            this.message = "The Todo was updated successfully!";
          })
          .catch((e: Error) => {
            console.log(e);
          });
      },
      deleteTodo() {
        TodoDataService.delete(this.currentTodo.id)
          .then((response: ResponseData) => {
            console.log(response.data);
            this.$router.push({ name: "todos" });
          })
          .catch((e: Error) => {
            console.log(e);
          });
      }
    }
  });
</script>

<style>
  .edit-form {
    max-width: 300px;
    margin: auto;
  }
</style>
