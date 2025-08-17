import React, { useEffect, useState } from "react";
import { getTasks } from "../api/backend";
import { Task } from "../api/types";
import Table, { TableColumn } from "../components/Table";
import Font from "../components/Font";

const columns: TableColumn<Task>[] = [
  { header: "ID", accessor: "id" },
  { header: "Descrição", accessor: "description" },
  { header: "Usuário", accessor: "user" },
];

const TasksTable: React.FC = () => {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchTasks = async () => {
      setLoading(true);
      try {
        const res = await getTasks();
        setTasks(res.data);
      } catch (e) {
        // erro silencioso
      } finally {
        setLoading(false);
      }
    };
    fetchTasks();
  }, []);

  return (
    <div className="container mb-5">
      <Font color="#1174de">Tabela Tasks</Font>
      {loading ? (
        <div>Carregando...</div>
      ) : (
        <Table
          columns={columns}
          data={tasks.map((t) => ({ ...t, user: 1 })) as any}
        />
      )}
    </div>
  );
};

export default TasksTable;
