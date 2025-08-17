import React from "react";
import "./App.css";

import Header from "./components/Header";

import TasksTable from "./pages/TasksTable";
import UsersTable from "./pages/UsersTable";

function App() {
  return (
    <>
      <Header title="Tasks de um usuário" />
      <UsersTable />
      <TasksTable />
    </>
  );
}

export default App;
