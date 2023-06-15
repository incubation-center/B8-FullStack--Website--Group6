// Store state globalizely
import { configureStore } from "@reduxjs/toolkit";
import signupReducer from "./slices/signupSlice";

const store = configureStore({
  reducer: {
    signup: signupReducer,
  },
});

export default store;

export type RootState = ReturnType<typeof store.getState>;
