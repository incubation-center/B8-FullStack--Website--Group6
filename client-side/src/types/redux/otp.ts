// Define state action

interface OtpState {
  otpCodes: Array<string>;
  activeOtpIndex: number;
}

const UpdateOtpAction = "OTP Verify";

export default OtpState;
export { UpdateOtpAction };
