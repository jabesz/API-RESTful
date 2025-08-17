import React, { useEffect, useState } from "react";
import { getUsers } from "../api/backend";
import { User } from "../api/types";
import Table, { TableColumn } from "../components/Table";
import Font from "../components/Font";

const UsersTable: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);

  const columns: TableColumn<User>[] = [
    { header: "ID", accessor: "id" },
    { header: "Usuário", accessor: "username" },
  ];

  useEffect(() => {
    const fetchUsers = async () => {
      setLoading(true);
      try {
        const res = await getUsers();
        setUsers(res.data);
      } catch (e) {
        // erro silencioso
      } finally {
        setLoading(false);
      }
    };
    fetchUsers();
  }, []);

  return (
    <div className="container mb-5">
      <Font color="#1174de">Tabela Usuarios</Font>
      {loading ? (
        <div>Carregando...</div>
      ) : (
        <Table columns={columns} data={users} />
      )}
    </div>
  );
};

export default UsersTable;
