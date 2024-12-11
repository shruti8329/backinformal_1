import React from 'react';
import { Row, Col, Card, Table } from 'react-bootstrap';
import logo from './image.png';

import './Invoice.css';
import { formatDate, numberToWords } from './NumTOWordAndDate';

const todayDate = new Date();

const Invoice = ({ billedForData, rows, stats, settings }) => {
  // const invoiceData = [
  //   {
  //     sr: "1.",
  //     item: "Custom Made T-Shirt",
  //     gstRate: "12%",
  //     quantity: 15,
  //     rate: "₹990.00",
  //     amount: "₹14,850.00",
  //     cgst: "₹891.00",
  //     sgst: "₹891.00",
  //     total: "₹16,632.00",
  //   },
  //   {
  //     sr: "2.",
  //     item: "Bandana With Logo",
  //     gstRate: "12%",
  //     quantity: 15,
  //     rate: "₹60.00",
  //     amount: "₹900.00",
  //     cgst: "₹54.00",
  //     sgst: "₹54.00",
  //     total: "₹1,008.00",
  //   },
  //   {
  //     sr: "3.",
  //     item: "Bandana With Logo",
  //     gstRate: "12%",
  //     quantity: 15,
  //     rate: "₹60.00",
  //     amount: "₹900.00",
  //     cgst: "₹54.00",
  //     sgst: "₹54.00",
  //     total: "₹1,008.00",
  //   },
  //   {
  //     sr: "4.",
  //     item: "Custom Made T-Shirt",
  //     gstRate: "12%",
  //     quantity: 15,
  //     rate: "₹990.00",
  //     amount: "₹14,850.00",
  //     cgst: "₹891.00",
  //     sgst: "₹891.00",
  //     total: "₹16,632.00",
  //   },
  // ];

  return (
    <div className="invoice-container">
      <header className="invoice-header">
        <div className="invoice-title">
          <p>Invoice</p>
          <div className="invoice-details">
            <p>
              Invoice No #<strong>{billedForData.invoiceNo}</strong>
            </p>
            <p>
              Invoice Date <strong>{formatDate(todayDate)}</strong>
            </p>
          </div>
        </div>
        <div className="invoice-logo">
          <img
            src={
              settings
                ? `/apiClient/api/business-logo?businessLogo=${settings.settingMaster.logoImage}`
                : logo
            }
            alt="Company Logo"
            style={{borderRadius:'999px'}}
          />
        </div>
      </header>

      <div className="billing-info">
        <div className="billed-by">
          <p1>Billed By</p1>
          <p>
            <strong>
              {settings ? (
                settings.settingMaster.companyName
              ) : (
                <>Business Name</>
              )}
            </strong>
          </p>
          <p>
            {settings ? (
              settings.settingMaster.companyAddress
            ) : (
              <>Business Address</>
            )}
          </p>
          <p>
            <strong>GSTIN:</strong>{' '}
            {settings ? settings.settingMaster.gstin : <>Business GSTIN</>}
          </p>
          <p>
            <strong>PAN:</strong>{' '}
            {settings ? settings.settingMaster.panNumber : <>Business PAN</>}
          </p>
        </div>
        <div className="billed-to">
          <p2>Billed To</p2>
          <p>
            <strong>{billedForData.companyName}</strong>
          </p>
          <p>{billedForData.address}</p>
          <p>
            <strong>GSTIN:</strong> {billedForData.gstNo}
          </p>
          <p>
            <strong>PAN:</strong> {billedForData.panNo}
          </p>
          <p>
            <strong>Email:</strong> {billedForData.email}
          </p>
          <p>
            <strong>Phone:</strong> {billedForData.phone}
          </p>
        </div>
      </div>

      <Table className="invoice-table">
        <thead>
          <tr>
            <th>Sr.No.</th>

            <th>Item</th>
            <th>GST Rate</th>
            <th>Quantity</th>
            <th>Rate</th>
            <th>Amount</th>
            <th>CGST</th>
            <th>SGST</th>
            <th>Total</th>
          </tr>
        </thead>
        <tbody>
          {rows.map((row, index) => (
            <tr key={index}>
              <td>{index + 1}</td>
              <td className="itemtd">{row.itemName}</td>
              <td>{row.gstRate}</td>
              <td>{row.quantity}</td>
              <td>{row.itemPrice}</td>
              <td>{(row.quantity * row.itemPrice).toFixed(2)}</td>
              <td>
                {(
                  (row.quantity * row.itemPrice * (row.gstRate / 100)) /
                  2
                ).toFixed(2)}
              </td>
              <td>
                {(
                  (row.quantity * row.itemPrice * (row.gstRate / 100)) /
                  2
                ).toFixed(2)}
              </td>
              <td>{row.totalPrice}</td>
            </tr>
          ))}
        </tbody>
      </Table>

      <div className="totalwrd">
        <p>
          <strong>Total (in words): {numberToWords(stats.netTotal)}</strong>
        </p>
      </div>

      <div className="details-container">
        <div className="bank-details">
          <h4>Bank Details</h4>
          <p>
            <strong>Account Name:</strong>{' '}
            <span>{settings.accountHolderName}</span>
          </p>
          <p>
            <strong>Account Number:</strong>{' '}
            <span>{settings.accountNumber}</span>
          </p>
          <p>
            <strong>IFSC:</strong> <span>{settings.ifscCode}</span>
          </p>
          <p>
            <strong>Account Type:</strong> <span>{settings.accountType}</span>
          </p>
          <p>
            <strong>Bank:</strong> <span>{settings.bankName}</span>
          </p>
        </div>
        <div className="total-details">
          <p>
            Sub Amount <span>₹{stats.subTotal}</span>
          </p>
          <p>
            CGST <span>₹{stats.taxAmount / 2}</span>
          </p>
          <p>
            SGST <span>₹ {stats.taxAmount / 2}</span>
          </p>
          <p>
            <strong>Total (INR)</strong> ₹ {stats.netTotal}
          </p>

          <p>
            Paid Amount{' '}
            <span style={{ color: 'green' }}>₹ {billedForData.paidAmount}</span>
          </p>
          <p>
            Due Amount{' '}
            <span style={{ color: 'red' }}>₹ {billedForData.dueAmount} </span>{' '}
          </p>
        </div>
      </div>
      <div style={{ margin: '5px', border: '1px solid grey', padding: '10px' }}>
        <p>{billedForData ? billedForData.remarkNote : <></>}</p>
      </div>
      <div className="support">
        For any enquiry, reach out via email at
        <strong> support@backinformal.com</strong>,
        <strong>call on +91 93730 12123</strong>
      </div>
    </div>
  );
};

export default Invoice;
