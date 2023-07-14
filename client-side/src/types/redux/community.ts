// Define state action
// interface FileMetadata {
//   name: string;
//   size: number;
//   type: string;
// }

export interface User {
  id: number;
  username: string;
  email: string;
}

interface CommunitySate {
  communityProfileData: string | ArrayBuffer | null;
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
