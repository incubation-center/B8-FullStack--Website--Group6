// Define state action
// interface FileMetadata {
//   name: string;
//   size: number;
//   type: string;
// }
interface CommunityState {
  userProfile: File | null;
  isCreateCommunityOpen: boolean;
}



const UpdateCreateCommunityAction = "Create Community";

export default CommunityState;
export { UpdateCreateCommunityAction };
