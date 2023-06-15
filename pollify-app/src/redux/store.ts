// Store state globalizely
import { configureStore } from "@reduxjs/toolkit";
import registerReducer from "./slices/RegisterForm";

const store = configureStore({
  reducer: {
    register: registerReducer,
  },
});

export default store;

export type RootState = ReturnType<typeof store.getState>;
