import React from 'react';
import { connect } from 'react-redux';
import Button from '../Button';
import { startSimulation, togglePoke, savefile, load} from '../../store/command/actions';
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
    <Button text="SIM" onClick={() => props.startSimulation()} />
    <Button text="SAVE" onClick={() => props.savefile()} />
    <Button text="LOAD" onClick={() => props.load()} />
    
  </div>
);

const mapDispatchToProps = {
  startSimulation,
  togglePoke,
  savefile,
  load,
};

export default connect(null, mapDispatchToProps)(Header);
