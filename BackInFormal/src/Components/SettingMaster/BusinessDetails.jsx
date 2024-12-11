import React, { useState, useEffect } from 'react';
import { Row, Col, Form, FloatingLabel } from 'react-bootstrap';
import { toast } from 'react-toastify';
import './Bank_details.css';
import axios from 'axios';

const BusinessDetails = ({ settings, fetchSettings }) => {
  const [compName, setCompName] = useState('');
  const [contactNumber, setContactNumber] = useState('');
  const [panNo, setPanNo] = useState('');
  const [imageLogo, setImageLogo] = useState(null);
  const [email, setEmail] = useState('');
  const [gstNo, setGstNo] = useState('');
  const [address, setAddress] = useState('');
  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (settings && settings.settingMaster) {
      const { settingMaster } = settings;
      setCompName(settingMaster.companyName || '');
      setContactNumber(settingMaster.companyMobile || '');
      setPanNo(settingMaster.panNumber || '');
      setEmail(settingMaster.companyEmail || '');
      setGstNo(settingMaster.gstin || '');
      setAddress(settingMaster.companyAddress || '');
    }
  }, [settings]);

  const handleInputChange = (setter) => (e) => {
    setter(e.target.value);
    setErrors({ ...errors, [e.target.name]: '' }); // Clear errors when user changes input
  };

  const handleContactNumberChange = (e) => {
    const value = e.target.value.replace(/\D/g, ''); // Remove non-digit characters
    setContactNumber(value);
    setErrors({ ...errors, contactNumber: '' });
  };

  const handlePanChange = (e) => {
    const value = e.target.value.toUpperCase(); // Convert input to uppercase
    // Restrict to valid PAN format: 5 uppercase letters, 4 digits, 1 uppercase letter
    if (/^[A-Z]{0,5}\d{0,4}[A-Z]{0,1}$/.test(value) && value.length <= 10) {
      setPanNo(value);
      setErrors({ ...errors, panNo: '' });
    } else {
      setErrors({ ...errors, panNo: 'Invalid PAN format: ABCDE1234F' });
    }
  };

  const handleGstChange = (e) => {
    const value = e.target.value.toUpperCase(); // Convert input to uppercase
    // Restrict to valid GST format: 2 digits, 5 uppercase letters, 4 digits, 1 uppercase letter, 1 digit, 1 uppercase letter, 1 digit
    if (
      /^\d{0,2}[A-Z]{0,5}\d{0,4}[A-Z]{0,1}\d{0,1}[A-Z]{0,1}\d{0,1}$/.test(
        value
      ) &&
      value.length <= 15
    ) {
      setGstNo(value);
      setErrors({ ...errors, gstNo: '' });
    } else {
      setErrors({ ...errors, gstNo: 'Invalid GST format: 22ABCDE1234F1Z5' });
    }
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const validTypes = ['image/jpeg', 'image/png'];
      if (validTypes.includes(file.type)) {
        setImageLogo(file);
      }else{
        toast.error('only jpg, jpeg and png file are allowed')
        setImageLogo(null)
      }
    }
  }

  const validateInputs = () => {
    const newErrors = {};

    if (!/^[A-Z]{5}\d{4}[A-Z]{1}$/.test(panNo)) {
      newErrors.panNo =
        'PAN number must be 10 alphanumeric characters in the format: ABCDE1234F';
    }

    if (!/^\d{2}[A-Z]{5}\d{4}[A-Z]{1}\d{1}[A-Z]{1}\d{1}$/.test(gstNo)) {
      newErrors.gstNo =
        'GST number must be 15 alphanumeric characters in the format: 22ABCDE1234F1Z5';
    }

    if (!/^\d{10}$/.test(contactNumber)) {
      newErrors.contactNumber = 'Contact number must be exactly 10 digits';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0; // Returns true if no errors
  };

  const handleBusinessUpdate = async (e) => {
    e.preventDefault();
    if (!validateInputs()) {
      return; // Don't proceed if validation fails
    }

    try {
      const settingsData = {
        companyName: compName,
        companyAddress: address,
        companyMobile: contactNumber,
        companyEmail: email,
        gstin: gstNo,
        panNumber: panNo,
      };

      const jsonBlob = new Blob([JSON.stringify(settingsData)], {
        type: 'application/json',
      });
      const formdata = new FormData();
      formdata.append('settingObj', jsonBlob, 'settings.json');

      if (imageLogo) {
        formdata.append('image', imageLogo);
      }

      const response = await axios.put(
        '/apiClient/settings/update-setting',
        formdata,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        }
      );

      if (response.status === 200) {
        fetchSettings();
        toast.success('Business settings updated successfully');
      } else {
        toast.error('Failed to update settings');
      }
    } catch (error) {
      console.error(error);
      toast.error('An error occurred while updating settings');
    }
  };

  return (
    <div
      style={{
        border: '1px solid black',
        margin: '25px',
        marginTop: '30px',
        padding: '25px',
        borderRadius: '10px',
      }}
    >
      <h3 className="mb-4 formHeadingSetting">Business Details</h3>
      <form onSubmit={handleBusinessUpdate}>
        <Row>
          <Col xs={6}>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputCompanyName"
                  label="Company Name"
                  className="mb-3"
                >
                  <Form.Control
                    type="text"
                    placeholder=""
                    value={compName}
                    onChange={handleInputChange(setCompName)}
                  />
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputContactNumber"
                  label="Contact Number"
                  className="mb-3"
                >
                  <Form.Control
                    type="tel"
                    inputMode="numeric" // Ensures numeric keypad on mobile
                    pattern="[0-9]*" // Restricts to numbers only
                    placeholder=""
                    value={contactNumber}
                    name="contactNumber"
                    onChange={handleContactNumberChange}
                    maxLength={10}
                    isInvalid={!!errors.contactNumber}
                  />
                  <Form.Control.Feedback type="invalid">
                    {errors.contactNumber}
                  </Form.Control.Feedback>
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputPanNo"
                  label="PAN No"
                  className="mb-3"
                >
                  <Form.Control
                    type="text"
                    placeholder="ABCDE1234F"
                    value={panNo}
                    name="panNo"
                    onChange={handlePanChange}
                    maxLength={10} // Restrict to 10 characters
                    isInvalid={!!errors.panNo}
                  />
                  <Form.Control.Feedback type="invalid">
                    {errors.panNo}
                  </Form.Control.Feedback>
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputImageLogo"
                  label="Upload Logo"
                  className="mb-3"
                >
                  <Form.Control type="file" onChange={handleFileChange} />
                </FloatingLabel>
              </Col>
            </Row>
          </Col>
          <Col xs={6}>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputCompanyEmail"
                  label="Company Email"
                  className="mb-3"
                >
                  <Form.Control
                    type="email"
                    placeholder=""
                    value={email}
                    onChange={handleInputChange(setEmail)}
                  />
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputGstin"
                  label="GSTIN"
                  className="mb-3"
                >
                  <Form.Control
                    type="text"
                    placeholder="22ABCDE1234F1Z5"
                    value={gstNo}
                    name="gstNo"
                    onChange={handleGstChange}
                    maxLength={15}
                    isInvalid={!!errors.gstNo}
                  />
                  <Form.Control.Feedback type="invalid">
                    {errors.gstNo}
                  </Form.Control.Feedback>
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputAddress"
                  label="Company Address"
                  className="mb-3"
                >
                  <Form.Control
                    type="text"
                    placeholder=""
                    value={address}
                    onChange={handleInputChange(setAddress)}
                  />
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={2}></Col>
              <Col md={8}>
                <button type="submit" className="save-detail">
                  Update details
                </button>
              </Col>
              <Col md={2}></Col>
            </Row>
          </Col>
        </Row>
      </form>
    </div>
  );
};

export default BusinessDetails;
