export interface User {
  id: number;
  username: string;
  password?: string;
}

export interface Task {
  id: number;
  description: string;
  user: User;
}
