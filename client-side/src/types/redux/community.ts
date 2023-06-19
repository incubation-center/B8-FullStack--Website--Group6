// Define state action
interface FileMetadata {
  name: string;
  size: number;
  type: string;
}
interface CommunitySate {
  userProfile: File | null;
}

const UpdateCreateCommunityAction = "Create Community";

export default CommunitySate;
export { UpdateCreateCommunityAction };
