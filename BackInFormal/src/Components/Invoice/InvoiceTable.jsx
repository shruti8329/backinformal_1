import React, { useState } from 'react';
import { Table, Button, Row, Col } from 'react-bootstrap';
import './invoicetavle.css';

const InvoiceTable = ({
  rows,
  handleInputChange,
  setRows,
  handleCheckboxChange,
}) => {
  return (
    <div className="table-container">
      <Row>
        <Col xs={12}>
          <Table bordered hover responsive className="tableOfInvoiceItem">
            <thead>
              <tr>
                <th style={{ width: '5%' }}>Select</th>
                <th style={{ width: '20%' }}>Item</th>
                <th style={{ width: '10%' }}>GST(%)</th>
                <th style={{ width: '10%' }}>Quantity</th>
                <th style={{ width: '10%' }}>Rate</th>
                <th style={{ width: '10%' }}>Amount</th>
                <th style={{ width: '10%' }}>CGST </th>
                <th style={{ width: '10%' }}>SGST</th>
                <th style={{ width: '10%' }}>Total</th>
              </tr>
            </thead>
            <tbody>
              {rows.map((row) => (
                <tr key={row.id}>
                  <td>
                    <input
                      type="checkbox"
                      checked={row.selected}
                      onChange={() => handleCheckboxChange(row.id)}
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      required
                      value={row.itemName}
                      onChange={(e) =>
                        handleInputChange(row.id, 'itemName', e.target.value)
                      }
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      value={row.gstRate}
                      onChange={(e) => {
                        if (e.target.value.length > 0 ) {
                          if (/^\d*\.?\d*$/.test(e.target.value) && e.target.value<=100) {
                            handleInputChange(
                              row.id,
                              'gstRate',
                              e.target.value
                            );
                          }
                        } else {
                          handleInputChange(row.id, 'gstRate', 0);
                        }
                      }}
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      required
                      value={row.quantity}
                      onChange={(e) => {
                        if (e.target.value.length > 0) {
                          if (/^\d*\.?\d*$/.test(e.target.value)) {
                            handleInputChange(
                              row.id,
                              'quantity',
                              e.target.value
                            );
                          }
                        } else {
                          handleInputChange(row.id, 'quantity', 0);
                        }
                      }}
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      required
                      value={row.itemPrice}
                      onChange={(e) => {
                        if (e.target.value.length > 0) {
                          if (/^\d*\.?\d*$/.test(e.target.value)) {
                            handleInputChange(
                              row.id,
                              'itemPrice',
                              e.target.value
                            );
                          }
                        } else {
                          handleInputChange(row.id, 'itemPrice', 0);
                        }
                      }}
                    />
                  </td>
                  <td>
                    <input type="text" value={row.amount.toFixed(2)} readOnly />
                  </td>
                  <td>
                    <input type="text" value={row.cgst.toFixed(2)} readOnly />
                  </td>
                  <td>
                    <input type="text" value={row.sgst.toFixed(2)} readOnly />
                  </td>
                  <td>
                    <input
                      type="text"
                      value={row.totalPrice.toFixed(2)}
                      readOnly
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Col>
      </Row>
    </div>
  );
};

export default InvoiceTable;
