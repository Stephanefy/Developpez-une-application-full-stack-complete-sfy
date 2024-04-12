import { User } from "./user.interface";

export interface AuthState {
    token?: string;
    user: User | null;
    isAuthenticated: boolean;
    error?: string | null;
}

