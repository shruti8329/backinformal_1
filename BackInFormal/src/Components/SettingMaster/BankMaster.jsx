import React, { useState, useEffect } from 'react';
import { Row, Col, Form, FloatingLabel } from 'react-bootstrap';
import axios from 'axios';
import { toast } from 'react-toastify';
import './Bank_details.css';

const Bank_details = ({ settings, fetchSettings }) => {
  // State hooks for each form field
  const [bankName, setBankName] = useState('');
  const [accountHolderName, setAccountHolderName] = useState('');
  const [accountNumber, setAccountNumber] = useState('');
  const [branchAddress, setBranchAddress] = useState('');
  const [accountType, setAccountType] = useState('');
  const [ifscCode, setIfscCode] = useState('');
  const [branchName, setBranchName] = useState('');

  // Errors state for validation
  const [errors, setErrors] = useState({});

  // Effect hook to populate form with existing data
  useEffect(() => {
    if (settings) {
      setBankName(settings.bankName || '');
      setAccountHolderName(settings.accountHolderName || '');
      setAccountNumber(settings.accountNumber || '');
      setBranchAddress(settings.branchAddress || '');
      setAccountType(settings.accountType || '');
      setIfscCode(settings.ifscCode || '');
      setBranchName(settings.branchName || '');
    }
  }, [settings]);

  // Validation function
  const validateInputs = () => {
    const newErrors = {};

    if (!bankName.trim()) {
      newErrors.bankName = 'Bank Name is required.';
    }

    if (!accountHolderName.trim()) {
      newErrors.accountHolderName = 'Account Holder Name is required.';
    } else if (accountHolderName.length > 25) {
      newErrors.accountHolderName =
        'Account Holder Name must not exceed 25 characters.';
    }

    if (!accountNumber.trim()) {
      newErrors.accountNumber = 'Account Number is required.';
    } else if (!/^\d{9,18}$/.test(accountNumber)) {
      newErrors.accountNumber =
        'Account Number must be between 9 to 18 digits. format:0110040500000013';
    }

    if (!branchAddress.trim()) {
      newErrors.branchAddress = 'Branch Address is required.';
    }

    if (accountType === 'Not Selected') {
      newErrors.accountType = 'Please select an account type.';
    }

    if (!ifscCode.trim()) {
      newErrors.ifscCode = 'IFSC Code is required.';
    } else if (!/^[A-Z]{4}\d{7}$/.test(ifscCode)) {
      newErrors.ifscCode =
        'IFSC Code must be in the format: 4 letters followed by 7 digits. format:SBIN0000051';
    }

    if (!branchName.trim()) {
      newErrors.branchName = 'Branch Name is required.';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0; // Returns true if no errors
  };

  // Handle form submission
  const handleFormSubmit = async (e) => {
    e.preventDefault();

    if (!validateInputs()) {
      return;
    }

    try {
      const response = await axios.put(
        '/apiClient/api/bankDetails/update-bank/1',
        {
          bankName,
          accountHolderName,
          accountNumber,
          branchAddress,
          accountType,
          ifscCode,
          branchName,
        },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );

      if (response.status === 200) {
        fetchSettings();
        toast.success('Bank Details updated successfully');
      }
    } catch (error) {
      console.error('Error updating bank details', error);
    }
  };

  // Change case for certain fields
  const changeCase = (event) => {
    event.preventDefault();
    setAccountHolderName(event.target.value.toUpperCase());
  };

  const changeBankNameCase = (event) => {
    event.preventDefault();
    setBankName(event.target.value.toUpperCase());
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
      <h3 className="mb-4 formHeadingSetting">Bank Details</h3>
      <form onSubmit={handleFormSubmit}>
        <Row>
          <Col xs={6}>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputBankName"
                  label="Bank Name"
                  className="mb-3"
                >
                  <Form.Control
                    type="text"
                    placeholder=""
                    value={bankName}
                    onChange={changeBankNameCase}
                  />
                  {errors.bankName && (
                    <small className="text-danger">{errors.bankName}</small>
                  )}
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputAccountHolderName"
                  label="Account Holder Name"
                  className="mb-3"
                >
                  <Form.Control
                    type="text"
                    placeholder=""
                    value={accountHolderName}
                    maxLength={25}
                    onChange={changeCase}
                  />
                  {errors.accountHolderName && (
                    <small className="text-danger">
                      {errors.accountHolderName}
                    </small>
                  )}
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputAccountNumber"
                  label="Account No."
                  className="mb-3"
                >
                  <Form.Control
                    type="text"
                    placeholder=""
                    value={accountNumber}
                    onChange={(e) =>
                      setAccountNumber(e.target.value.replace(/\D/g, ''))
                    }
                  />
                  {errors.accountNumber && (
                    <small className="text-danger">
                      {errors.accountNumber}
                    </small>
                  )}
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputBranchAddress"
                  label="Branch Address"
                  className="mb-3"
                >
                  <Form.Control
                    type="text"
                    placeholder=""
                    value={branchAddress}
                    onChange={(e) => setBranchAddress(e.target.value)}
                  />
                  {errors.branchAddress && (
                    <small className="text-danger">
                      {errors.branchAddress}
                    </small>
                  )}
                </FloatingLabel>
              </Col>
            </Row>
          </Col>
          <Col xs={6}>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingSelectAccountType"
                  className="mb-3"
                >
                  <Form.Select
                    placeholder=""
                    value={accountType}
                    onChange={(e) => setAccountType(e.target.value)}
                  >
                    <option value="Not Selected">Select Type</option>
                    <option value="saving">Saving Account</option>
                    <option value="current">Current Account</option>
                    <option value="recurring">Recurring Account</option>
                  </Form.Select>
                  {errors.accountType && (
                    <small className="text-danger">{errors.accountType}</small>
                  )}
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputIfscCode"
                  label="IFSC Code"
                  className="mb-3"
                >
                  <Form.Control
                    type="text"
                    placeholder=""
                    value={ifscCode}
                    maxLength={11}
                    onChange={(e) => setIfscCode(e.target.value.toUpperCase())}
                  />
                  {errors.ifscCode && (
                    <small className="text-danger">{errors.ifscCode}</small>
                  )}
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={12}>
                <FloatingLabel
                  controlId="floatingInputBranchName"
                  label="Branch Name"
                  className="mb-3"
                >
                  <Form.Control
                    type="text"
                    placeholder=""
                    value={branchName}
                    onChange={(e) => setBranchName(e.target.value)}
                  />
                  {errors.branchName && (
                    <small className="text-danger">{errors.branchName}</small>
                  )}
                </FloatingLabel>
              </Col>
            </Row>
            <Row>
              <Col md={2}></Col>
              <Col md={8}>
                <button type="submit" className="save-detail">
                  Update Details
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

export default Bank_details;
