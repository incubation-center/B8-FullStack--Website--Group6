// Define state action

export interface User {
  id: number;
  username: string;
  email: string;
}

interface CommunitySate {
  userCommunity: number;
  communityName: string;
  inCommunityId: number;
  searchTerm: string;
  communityDescription: string;
  isCreateCommunityOpen: boolean;
  isBackToCommunity: boolean;
  isCommunityProfileOpen: boolean;
  userData: User[];
  invitedUsers: User[];
  communityMembers: User[];
}

const UpdateCommunityAction = "Community";

export default CommunitySate;
export { UpdateCommunityAction };
