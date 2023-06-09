// Define state reducer
import SignupState, { UpdateSignupAction } from "../../types/redux/signup";
import { createSlice, PayloadAction } from "@reduxjs/toolkit";

const initialState: SignupState = {
  username: "",
  email: "",
  password: "",
};

export const signupSlice = createSlice({
  name: UpdateSignupAction,
  initialState: initialState,
  reducers: {
    setUsername: (state, action: PayloadAction<string>) => {
      state.username = action.payload;
    },
  },
});

// Action creators are generated for each case reducer function
export const { setUsername } = signupSlice.actions;

export default signupSlice.reducer;
