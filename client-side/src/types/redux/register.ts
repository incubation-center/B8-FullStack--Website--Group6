// Define state action

export interface ErrorMessage {
  usernameMessage: string;
  emailMessage: string;
  passwordMessage: string;
}

interface RegisterState {
  firstname: string;
  lastname: string;
  username: string;
  email: string;
  password: string;
  isAgree: boolean;
  errorMessage: ErrorMessage;
}

const UpdateRegisterAction = "Sign Up";

export default RegisterState;
export { UpdateRegisterAction };
