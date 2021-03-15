import React from 'react';
import { connect } from 'react-redux';
import Button from '../Button';
import { startSimulation, togglePoke, loadCircuit, saveCircuit } from '../../store/command/actions';
import { headerBackgroundColor, headerHeight } from '../../util/style';

/**
 * The header bar in the web app, load this with buttons that the user can use to
 * save/load/simulate, etc
 * @author:kyhorne
 */
const headerStyle = {
  width: 'inherit',
  backgroundColor: headerBackgroundColor,
  height: `${headerHeight}vh`,
  display: 'flex',
  alignItems: 'center',
};

const Header = (props) => (
  <div style={{ ...headerStyle }}>
    <Button text="TOGGLE POKE" onClick={() => props.togglePoke()} />
    <Button text="SIMULATE" onClick={() => props.startSimulation()} />
    <Button text="LOAD CIRCUIT" onClick={() => props.loadCircuit()} />
    <Button text="SAVE CIRCUIT" onClick={() => props.saveCircuit()} />
  </div>
);

const mapDispatchToProps = {
  startSimulation,
  togglePoke,
  loadCircuit,
  saveCircuit
};

export default connect(null, mapDispatchToProps)(Header);
