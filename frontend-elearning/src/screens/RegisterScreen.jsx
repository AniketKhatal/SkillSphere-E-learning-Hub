import React, { useEffect, useState } from "react"; // Import necessary hooks from React
import ReCAPTCHA from "react-google-recaptcha"; // Import ReCAPTCHA component
import { useDispatch, useSelector } from "react-redux"; // Import Redux hooks
import { useNavigate } from "react-router-dom"; // Import navigation hook
import {
  Button, Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row
} from "reactstrap"; // Import Reactstrap components
import { logout, register } from "../actions/userActions"; // Import actions
import { onlyChar, onlyPhone } from "../data/Regex"; // Import custom regex
import Select from "react-select"; // Import Select component

function RegisterScreen() {
  // State management for form fields
  const [userName, setUserName] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [pass, setPass] = useState("");
  const [address, setAddress] = useState("");
  const [category, setCategory] = useState({});
  const [phoneNo, setPhoneNo] = useState("");
  
  // State management for error handling
  const [pwdError, setPwdError] = useState(false);
  const [phError, setPhError] = useState(false);
  const [unameError, setUnameError] = useState(false);
  const [fnameError, setFnameError] = useState(false);
  const [lnameError, setLnameError] = useState(false);
  const [emailError, setEmailError] = useState(false);
  const [addressError, setAddressError] = useState(false);

  // Access Redux store and dispatch actions
  const dispatch = useDispatch();
  const userRegister = useSelector((state) => state.userRegister);
  const { error, loading, userInfo } = userRegister;

  const navigate = useNavigate();

  // Side effect for user registration success
  useEffect(() => {
    if (userInfo) {
      localStorage.clear();
      dispatch(logout());
      navigate("/login");
    }
  }, [userInfo, navigate]);

  // Form submission handler
  const submitHandler = (e) => {
    e.preventDefault();
    let flag = true;
    if (!onlyChar.test(userName)) setUnameError(true);
    if (!onlyChar.test(firstName)) setFnameError(true);
    if (!onlyChar.test(lastName)) setLnameError(true);
    if (pass.length < 6) setPwdError(true);
    if (address.length < 25) setAddressError(true);
    if (!onlyPhone.test(phoneNo)) setPhError(true);
    else flag = false;

    if (!flag) {
      dispatch(register(userName, firstName, lastName, email, pass, address, phoneNo, category.value));
    }
  };

  // Reset form fields and errors
  const reset = () => {
    setUserName(""); setFirstName(""); setLastName(""); setEmail(""); setPass(""); setAddress(""); setCategory({}); setPhoneNo("");
    setPwdError(false); setPhError(false); setUnameError(false); setFnameError(false); setLnameError(false); setEmailError(false); setAddressError(false);
  };

  // Options for Select component
  const option = [
    { value: 2, label: "Instructor" },
    { value: 3, label: "Student" },
  ];

  // State and handler for ReCAPTCHA verification
  const [Verifed, setVerifed] = useState(false);
  function onChange(value) {
    setVerifed(true);
  }

  // Render registration form
  return (
    <Container className="my-5">
      <Row className="pt-5">
        <Col sm={{ size: 6, offset: 3 }}>
          <Card color="dark" outline>
            <CardHeader className="text-center">
              <h3>Register</h3>
            </CardHeader>
            <CardBody>
              <Form onSubmit={submitHandler}>
                <FormGroup>
                  <Label for="firstName">User Name</Label>
                  <Input id="userName" placeholder="Enter user name" type="text" value={userName} onChange={(e) => setUserName(e.target.value)} />
                  {unameError && <p style={{ color: "red" }}> Enter valid username.</p>}
                </FormGroup>
                <FormGroup>
                  <Label for="firstName">First Name</Label>
                  <Input id="firstName" placeholder="Enter first name" type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                  {fnameError && <p style={{ color: "red" }}>Only text data allowed.</p>}
                </FormGroup>
                <FormGroup>
                  <Label for="lastName">Last Name</Label>
                  <Input id="lastName" placeholder="Enter last name" type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                  {lnameError && <p style={{ color: "red" }}>Only text data allowed.</p>}
                </FormGroup>
                <FormGroup>
                  <Label for="userEmail">Email</Label>
                  <Input id="userEmail" placeholder="Enter email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                  {emailError && <p style={{ color: "red" }}>Enter valid Email-id.</p>}
                </FormGroup>
                <FormGroup>
                  <Label for="userPassword">Password</Label>
                  <Input id="userPassword" placeholder="password placeholder" type="password" value={pass} onChange={(e) => setPass(e.target.value)} />
                  {pwdError && <p style={{ color: "red" }}>Please enter min 6 digits password.</p>}
                </FormGroup>
                <FormGroup>
                  <Label for="phoneNo">Phone No.</Label>
                  <Input id="phoneNo" placeholder="Enter phone no" type="number" value={phoneNo} onChange={(e) => setPhoneNo(e.target.value)} />
                  {phError && <p style={{ color: "red" }}>Please enter min 10 mobile no.</p>}
                </FormGroup>
                <FormGroup>
                  <Label for="userAddress">Address</Label>
                  <Input id="userAddress" type="textarea" value={address} onChange={(e) => setAddress(e.target.value)} />
                  {addressError && <p style={{ color: "red" }}>Address length should be Min 10 char required</p>}
                </FormGroup>
                <Label for="">Select Roll</Label>
                <Select value={category} onChange={(item) => setCategory(item)} options={option} placeholder={category === {} ? "Select Category" : category.label} required />
                <div className="mt-3" style={{marginLeft:"13px",marginBottom:"20px"}} >
                  <ReCAPTCHA sitekey="your_site_key" onChange={onChange} />
                </div>
                <Container className="text-center">
                  <Button color="dark" outline type="submit" disabled={!Verifed}>Register</Button>
                  <Button onClick={reset} className="ms-2" color="secondary" outline>Reset</Button>
                </Container>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default RegisterScreen;
