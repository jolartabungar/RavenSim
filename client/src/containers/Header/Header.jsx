import React, { useState, useEffect} from 'react';
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


const Header = (props) => {
  const localStorageKey = 'myValueInLocalStorage';
  const circuitNamesKey = 'circuitNames';

  const [fileName, setFileName] = useState(
    localStorage.getItem(localStorageKey) || ''
  );
  
  // might be better to use useRef here
  const [isSaveClicked, setIsSaveClicked] = useState(false);

  const onChange = event => {
    isSaveClicked && setIsSaveClicked(false);
    setFileName(event.target.value)
  };
  
  const onSaveClicked = fileName => {
    setIsSaveClicked(true);
    return props.saveCircuit(fileName)
  };

  const onLoadClicked = fileName => {
    // use updatedCircuitNames to get the circuits saved
    // in here, give the user the option to pick from the list of circuits saved 
    // using a pop up option of the list, or a dropdown menu of the list 
    // then load the circuit which the user has selected
    // so fileName will be equal to the selected option from the user
    console.log(localStorage.getItem(circuitNamesKey))
  }


  useEffect(() => {
    if (!fileName || !fileName.trim() || !isSaveClicked){
      return;
    }

    const circuitNames = JSON.parse(localStorage.getItem(circuitNamesKey)) || [];
    const updatedcircuitNames = circuitNames
      .filter((currFileName) => currFileName !== fileName);
    updatedcircuitNames.push(fileName);
    
    localStorage.setItem(circuitNamesKey, JSON.stringify(updatedcircuitNames));
    localStorage.setItem(localStorageKey, fileName);
  }, [isSaveClicked]);

  return (
    <div style={{ ...headerStyle }}>
      <input value={fileName} type="text" onChange={onChange} />
      <Button text="TOGGLE POKE" onClick={() => props.togglePoke()} />
      <Button text="SIMULATE" onClick={() => props.startSimulation()} />
      <Button
        text="LOAD CIRCUIT" 
        // onClick={onLoadClicked}
        onClick={(fileName) => props.loadCircuit(fileName)}
      />
      <Button
        text="SAVE CIRCUIT"
        onClick={onSaveClicked} 
      />
    </div>
  );
};

const mapDispatchToProps = {
  startSimulation,
  togglePoke,
  loadCircuit,
  saveCircuit
};

export default connect(null, mapDispatchToProps)(Header);
