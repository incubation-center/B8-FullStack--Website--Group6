// Define state action
interface SignupState {
  username: string;
  email: string;
  password: string;
}

const UpdateSignupAction = "Sign Up";

export default SignupState;
export { UpdateSignupAction };
