import axios from 'axios';
import { useState } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

const ForgetPassword = () => {
  const [email, setEmail] = useState('');
  const [oldPassword, setOldPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const navigate = useNavigate();

  const userLogIn = () => {
    navigate('/');
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    // console.log(email + ' ' + oldPassword, newPassword);
    try {
      if (newPassword === confirmPassword) {
        const response = await axios.post(
          `/apiClient/users/forget-password`,
          {
            email: email,
            oldPassword: oldPassword,
            newPassword: newPassword,
          }
        );

        if (response.status === 200) {
          console.log(email, oldPassword, newPassword, confirmPassword);
          setEmail('')
          setOldPassword('')
          setNewPassword('')
          setConfirmPassword('')
          

          toast.success('Password update successfully, Please Login..');
          navigate('/');
        }
      } else {
        toast.error('New Password does not match with confirm password');
      }
    } catch (error) {
      console.log(error);
      toast.error(error.response.data);
    }
  };

  return (
    <Row>
      <Col xs={4}></Col>
      <Col xs={4} className="formFieldInLogin">
        <Row className="loginFormInLOginPage">
          <Col xs={12}>
            <h2 style={{ color: '#db634a', textAlign: 'center' }}>
              Forget Password
            </h2>
          </Col>
          <Col xs={12}>
            <form onSubmit={(e) => handleUpdate(e)}>
              <label htmlFor="exampleInputEmail1">User Email</label>
              <br />
              <div className="formGRoup">
                <input
                  required
                  type="text"
                  name=""
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>

              <label htmlFor="exampleInputEmail1">Old Password</label>
              <br />
              <div className="formGRoup">
                <input
                  required
                  type="password"
                  name=""
                  onChange={(e) => setOldPassword(e.target.value)}
                />
              </div>

              <label htmlFor="exampleInputEmail1">New Password</label>
              <br />
              <div className="formGRoup">
                <input
                  required
                  type="password"
                  name=""
                  onChange={(e) => setNewPassword(e.target.value)}
                />
              </div>

              <label htmlFor="exampleInputEmail1">Confirm Password</label>
              <br />
              <div className="formGRoup">
                <input
                  required
                  type="password"
                  name=""
                  onChange={(e) => setConfirmPassword(e.target.value)}
                />
              </div>

              <Button type="submit" className="LoginBtnInLoginPage">
                Update
              </Button>
              <p
                onClick={userLogIn}
                style={{
                  display: 'inline',
                  cursor: 'pointer',
                  color: 'white',
                  marginLeft: '175px',
                }}
              >
                Log In
              </p>
            </form>
          </Col>
        </Row>
      </Col>
      <Col xs={4}></Col>
    </Row>
  );
};
export default ForgetPassword;
