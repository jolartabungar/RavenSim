import React, {Component} from 'react';
import { connect } from 'react-redux';
import Button from '../Button';
import { startSimulation, togglePoke, loadCircuit, saveCircuit } from '../../store/command/actions';
import { headerBackgroundColor, headerHeight } from '../../util/style'

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


// TODO: you need to modify this and replace the const fileName
// with the fileName specified by the user
const Header = (props) => (
  <div style={{ ...headerStyle }}>
    {/* // put a place for user to type filename, and when save is pressed, it takes the filename from there */}
    <Button text="TOGGLE POKE" onClick={() => props.togglePoke()} />
    <Button text="SIMULATE" onClick={() => props.startSimulation()} />
    <Button
      text="LOAD CIRCUIT" 
      onClick={(fileName=prompt('load file')) => props.loadCircuit(fileName)}
    />
    <Button
      text="SAVE CIRCUIT"
      onClick={(fileName= prompt('save file as')) => props.saveCircuit(fileName)} 
    />
  </div>
);

const mapDispatchToProps = {
  startSimulation,
  togglePoke,
  loadCircuit,
  saveCircuit
};

export default connect(null, mapDispatchToProps)(Header);
