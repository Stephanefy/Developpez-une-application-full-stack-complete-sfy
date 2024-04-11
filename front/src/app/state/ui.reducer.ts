import { createReducer, on } from "@ngrx/store";
import { toggleSidebar } from "./ui.action";



export const uiReducer = createReducer(
    { showSidebar: false },
    on(toggleSidebar, (state, { showSidebar }) => ({ ...state, showSidebar })),
);