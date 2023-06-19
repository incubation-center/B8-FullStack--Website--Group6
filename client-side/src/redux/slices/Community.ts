// Define state reducer
import CommunityState, {
  UpdateCreateCommunityAction,
} from "../../types/redux/community";
import { createSlice, PayloadAction } from "@reduxjs/toolkit";

const initialState: CommunityState = {
  userProfile: null,
};

export const communitySlice = createSlice({
  name: UpdateCreateCommunityAction,
  initialState: initialState,
  reducers: {
    setUserProfile: (state, action: PayloadAction<File | null>) => {
      state.userProfile = action.payload;
    },
  },
});

// Action creators are generated for each case reducer function
export const { setUserProfile } = communitySlice.actions;

export default communitySlice.reducer;
