<template>
  <div class="vue-todo">
    <h1>ToDo List</h1>
    <div class="todo">
      <div class="check-all-input">
        <input
          type="checkbox"
          class="check"
          v-bind:checked="!finished"
          v-on:change="checkAll"
        />
        <input
          class="input"
          v-model="newTodo"
          type="text"
          placeholder="Enter What You have to do"
          @keyup.enter="addTodo"
        />
      </div>
      <div
        v-for="(todo, index) in todoFilter"
        :key="todo.id"
        class="todo-items"
      >
        <input
          type="checkbox"
          class="check"
          v-model="todo.completed"
          @change="saveTodo"
        />
        <div :class="{ complete: todo.completed }" class="task">
          <div
            v-if="!todo.editItem"
            @dblclick="editTodo(todo)"
            class="todo-label"
          >
            {{ todo.task }}
          </div>
          <input
            v-else
            class="todo-input"
            @focusout="doneEdit(todo)"
            @blur="doneEdit(todo)"
            @keyup.enter="doneEdit(todo)"
            type="text"
            v-model="todo.task"
          />
        </div>

        <div
          class="remove"
          @click="removetodo(index)"
          v-show="showByIndex === index"
        >
          X
        </div>
      </div>
      <div class="filter-box">
        <div class="filter" v-if="todos.length === 0">There is no task</div>
        <div class="filter" v-if="todos.length > 0 && remaining === 0">
          No task left
        </div>
        <div class="filter" v-if="todos.length > 0 && remaining > 0">
          {{ remaining }} tasks left
        </div>
        <div class="filter">
          <button
            :class="{ active: filterTodo == 'all' }"
            class="filter-btn"
            @click="filterTodo = 'all'"
          >
            All
          </button>
          <button
            :class="{ active: filterTodo == 'active' }"
            class="filter-btn"
            @click="filterTodo = 'active'"
          >
            Active
          </button>
          <button
            :class="{ active: filterTodo == 'complete' }"
            class="filter-btn"
            @click="filterTodo = 'complete'"
          >
            Completed
          </button>
        </div>
        <div>
          <button v-if="show" @click="clearCompleted" class="filter-btn">
            Clear Completed
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "DemoProj",
  data() {
    return {
      newTodo: "",
      dateRestore: "",
      todoId: 3,
      filterTodo: "all",
      todos: [
        {
          id: 1,
          task: "Go To Office",
          completed: false,
          editItem: false,
        },
        {
          id: 2,
          task: "Drink Coffee",
          completed: false,
          editItem: false,
        },
      ],
      showByIndex: null,
    };
  },
  mounted() {
    if (localStorage.getItem("todos")) {
      this.todos = JSON.parse(localStorage.getItem("todos"));
    }
  },
  computed: {
    remaining() {
      return this.todos.filter((todo) => !todo.completed).length;
    },
    finished() {
      return this.remaining != 0;
    },
    todoFilter() {
      if (this.filterTodo == "active") {
        return this.todos.filter((todo) => !todo.completed);
      } else if (this.filterTodo == "complete") {
        return this.todos.filter((todo) => todo.completed);
      } else {
        return this.todos;
      }
    },
    show() {
      return this.todos.filter((todo) => todo.completed).length > 0;
    },
  },
  methods: {
    addTodo() {
      if (!this.newTodo.trim()) {
        return;
      }
      this.todos.push({
        id: this.todoId,
        task: this.newTodo,
        completed: false,
        editItem: false,
      });
      this.newTodo = "";
      this.todoId++;
      this.saveTodo();
    },
    editTodo(todo) {
      this.dateRestore = todo.task;
      todo.editItem = true;
    },
    doneEdit(todo) {
      if (!todo.task.trim()) {
        todo.task = this.dateRestore;
      }
      todo.editItem = false;
      this.saveTodo();
    },
    removetodo(index) {
      this.todos.splice(index, 1);
      this.saveTodo();
    },
    checkAll() {
      this.todos.forEach((todo) => (todo.completed = event.target.checked));
      this.saveTodo();
    },
    clearCompleted() {
      this.todos = this.todos.filter((todo) => !todo.completed);
      this.saveTodo();
    },
    saveTodo() {
      const parsed = JSON.stringify(this.todos);
      localStorage.setItem("todos", parsed);
    },
  },
};
</script>

<style>
/* .vue-todo {
} */
h1 {
  font-size: 3rem;
  color: slategrey;
}

.todo {
  margin: 0 auto;
  width: 60%;
  box-shadow: 7px 7px 4px 0 rgb(0 0 0 / 20%), 0 25px 50px 0 rgb(0 0 0 / 10%);
}

.task {
  display: flex;
  align-items: center;
  width: 80%;
  text-align: start;
  margin-left: 15px;
}
.check-all-input {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  padding: 0 5px;
  border: 1px solid #cccc;
}
.input {
  width: 80%;
  font-family: cursive;
  padding: 10px;
  font-size: 22px;
  border: none;
}
.todo-items {
  margin: 0 auto;
  display: flex;
  align-items: center;
  font-size: 18px;
  box-sizing: border-box;
  padding: 8px 5px;
  box-shadow: 0 0 1px grey;
}
.todo-label {
  width: 100%;
}
.todo-input {
  font-family: cursive;
  font-size: 16px;
  color: #2c3e50;
  width: 100%;
  text-align: start;
  padding: 2px 10px;
  border: 1px solid #ccc;
}

.todo-input:focus {
  outline: none;
}

.check {
  width: 5%;
}

.complete {
  text-decoration: line-through;
  color: #cccc;
}

.remove {
  font-size: 18px;
  font-weight: bold;
  color: rgb(94, 88, 88);
  cursor: pointer;
  margin-left: 10px;
  width: 10%;
}

.remove:hover {
  color: black;
}

.todo-items:hover > .remove {
  display: contents !important;
}
.filter-box {
  display: flex;
  vertical-align: middle;
  padding: 10px;
  justify-content: space-between;
}
.filter {
  margin: 0 5px;
}
.filter-btn {
  margin: 0 2.5px;
  background: transparent;
  border: none;
}
.filter-btn:hover,
.active {
  border: 1px solid #9b8b8bc7;
  border-radius: 5px;
  box-shadow: 2px 2px 0 0 #708090;
  cursor: pointer;
}
</style>