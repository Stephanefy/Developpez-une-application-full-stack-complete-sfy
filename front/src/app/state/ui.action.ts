import { createAction, props } from "@ngrx/store";

export const toggleSidebar = createAction('[UI] Toggle Sidebar', props<{ showSidebar: boolean }>())