import { AuthState } from "../interfaces/auth.interface";
import { UIState } from "../interfaces/ui.interface";

export interface AppState {
    auth: AuthState;
    ui: UIState;
}