// Define state reducer
import CommunityState, {
  UpdateCommunityAction,
  User,
} from "../../types/redux/community";
import { createSlice, PayloadAction } from "@reduxjs/toolkit";

const initialState: CommunityState = {
  userProfile: null,
  userCommunity: 0,
  communityName: "",
  inCommunityId: 0,
  searchTerm: "",
  communityDescription: "Description",
  isCreateCommunityOpen: false,
  userData: [],
  invitedUsers: [],
};

export const communitySlice = createSlice({
  name: UpdateCommunityAction,
  initialState: initialState,
  reducers: {
    setUserProfile: (state, action: PayloadAction<File | null>) => {
      state.userProfile = action.payload;
    },
    setUserCommunity: (state, action: PayloadAction<number>) => {
      state.userCommunity = action.payload;
    },
    setInCommunityId: (state, action: PayloadAction<number>) => {
      state.inCommunityId = action.payload;
    },
    setCommunityName: (state, action: PayloadAction<string>) => {
      state.communityName = action.payload;
    },
    setSearchTerm: (state, action: PayloadAction<string>) => {
      state.searchTerm = action.payload;
    },
    openCreateCommunity: (state) => {
      state.isCreateCommunityOpen = true;
    },
    closeCreateCommunity: (state) => {
      state.isCreateCommunityOpen = false;
    },
    setUserData: (state, action: PayloadAction<User[]>) => {
      state.userData = action.payload;
    },
    setInvitedUsers: (state, action: PayloadAction<User[]>) => {
      state.invitedUsers = action.payload;
    },
  },
});

// Action creators are generated for each case reducer function
export const {
  setUserProfile,
  setUserCommunity,
  setCommunityName,
  setInCommunityId,
  setSearchTerm,
  openCreateCommunity,
  closeCreateCommunity,
  setUserData,
  setInvitedUsers,
} = communitySlice.actions;

export default communitySlice.reducer;
