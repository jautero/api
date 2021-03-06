import React from 'react'
import { connect } from 'react-redux'
const { Col, Row } = require('react-flexbox-grid');

import { memberUpdate } from '../actions'
import Member from './Member'

const MemberList = ({ memberUpdate, people = [] }) => <Row>
  <Col
    xs={12}
    smOffset={1} sm={10}
    mdOffset={2} md={8}
  >{
    people.map(member => <Member
      key={member.get('id')}
      member={member}
      onUpdate={memberUpdate}
    />)
  }</Col>
</Row>;

export default connect(
  (state) => ({
    people: state.user.get('people')
  }), {
    memberUpdate
  }
)(MemberList);
