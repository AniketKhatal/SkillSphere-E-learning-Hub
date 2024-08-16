import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";

// Function component for the 'Aproove' form
function Aproove() {
  // State variables to store form data
  const [qualify, setQualify] = useState("");
  const [exp, setExp] = useState("");
  const [cert, setCert] = useState("");
  const [desc, setDesc] = useState("");
  const [status, setStatus] = useState("no");
  const [uid, setUid] = useState();

  // Accessing the user login state from Redux store
  const userLogin = useSelector((state) => state.userLogin);
  const { userInfo } = userLogin;

  // useEffect hook to get the user ID by name after component mounts
  useEffect(() => {
    const getIdByName = async () => {
      try {
        const config = {
          headers: {
            "Content-type": "application/json",
          },
        };
        // Making a POST request to get the user ID based on user name
        const { data } = await axios.post(
          "http://localhost:9090/api/elearning/getuid",
          { userName: userInfo },
          config
        );

        // Setting the user ID state
        setUid(data);
      } catch (error) {
        console.log(error);
      }
    };

    getIdByName();
  }, [userInfo]); // Dependency array includes userInfo to re-run the effect if userInfo changes

  // Form submit handler
  const submitHandler = (e) => {
    e.preventDefault(); // Preventing the default form submission behavior

    const submi = async () => {
      try {
        const config = {
          headers: {
            "Content-type": "application/json",
          },
        };
        // Making a POST request to submit the approval form data
        const { data } = await axios.post(
          "http://localhost:9090/api/elearning/instructor/getapprove",
          {
            qualification: qualify,
            exp: exp,
            certi: cert,
            desc: desc,
            status: status,
            uiId: uid,
          },
          config
        );

        // Alerting the user with the response data
        alert(data);

        // Resetting the form fields
        setCert("");
        setExp("");
        setQualify("");
        setDesc("");
      } catch (error) {
        console.log(error);
      }
    };

    submi();
  };

  return (
    <Container className="my-5">
      <Row className="pt-5">
        <Col sm={{ size: 6, offset: 3 }}>
          <Card color="dark" outline>
            <CardHeader className="text-center">
              <h3>Get Approve</h3>
            </CardHeader>
            <CardBody>
              <Form onSubmit={submitHandler}>
                <FormGroup>
                  <Label for="userName">Full Name</Label>
                  <Input
                    id="userName"
                    placeholder="Enter qualification"
                    type="text"
                    value={qualify}
                    onChange={(e) => setQualify(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="experience">Experience</Label>
                  <Input
                    id="experience"
                    placeholder="Enter your experience"
                    type="text"
                    value={exp}
                    onChange={(e) => setExp(e.target.value)}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="certifications">Certifications</Label>
                  <Input
                    id="certifications"
                    placeholder="Enter your certifications"
                    type="text"
                    value={cert}
                    onChange={(e) => setCert(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="description">Description</Label>
                  <Input
                    id="description"
                    type="textarea"
                    value={desc}
                    onChange={(e) => setDesc(e.target.value)}
                  />
                </FormGroup>
                <Container className="text-center">
                  <Button type="submit" color="success">
                    Submit
                  </Button>
                </Container>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default Aproove;
