import React from "react";

export interface TableColumn<T> {
  header: string;
  accessor: keyof T;
}

interface TableProps<T> {
  columns: TableColumn<T>[];
  data: T[];
}

function Table<T extends { id?: string | number }>({
  columns,
  data,
}: TableProps<T>) {
  return (
    <div className="table-responsive">
      <table className="table custom-table custom-table-striped table-hover align-middle">
        <thead>
          <tr>
            {columns.map((col) => (
              <th key={String(col.accessor)} scope="col">
                {col.header}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {data.map((row, i) => (
            <tr key={row.id ?? i}>
              {columns.map((col) => (
                <td key={String(col.accessor)}>{String(row[col.accessor])}</td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Table;
