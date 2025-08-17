import axios from 'axios';
import { User, Task } from './types';

const api = axios.create({
  baseURL: 'http://localhost:3030',
});

export const getUsers = () => api.get<User[]>('/users');
export const getUserById = (id: number) => api.get<User>(`/users/${id}`);
export const createUser = (user: Omit<User, 'id'>) => api.post<User>('/users', user);
export const updateUser = (id: number, user: Partial<User>) => api.put<User>(`/users/${id}`, user);
export const deleteUser = (id: number) => api.delete(`/users/${id}`);

export const getTasks = () => api.get<Task[]>('/tasks');
export const getTaskById = (id: number) => api.get<Task>(`/tasks/${id}`);
export const createTask = (task: Omit<Task, 'id'>) => api.post<Task>('/tasks', task);
export const updateTask = (id: number, task: Partial<Task>) => api.put<Task>(`/tasks/${id}`, task);
export const deleteTask = (id: number) => api.delete(`/tasks/${id}`);

export default api;
