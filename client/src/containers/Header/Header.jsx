import React, { useState, useEffect} from 'react';
import { connect } from 'react-redux';
import Button from '../Button';
import { startSimulation, togglePoke, loadCircuit, saveCircuit } from '../../store/command/actions';
import { headerBackgroundColor, headerHeight, textColor } from '../../util/style'
import { makeStyles, TextField } from '@material-ui/core';
import { Autocomplete } from '@material-ui/lab'

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
  justifyContent: 'space-between',
};

const groupStyle = {
  display: 'flex',
  alignItems: 'center',
}

// Styles used for Material UI Autocomplete component
const useStyles = makeStyles((theme) => ({
  root: {
    "& .MuiInputLabel-outlined:not(.MuiInputLabel-shrink)": {
      transform: "translate(10px, 11px) scale(1);"
    },
    '& .MuiFormLabel-root': {
      color: `${textColor}`,
    }
  },
  endAdornment: {
    '& .MuiIconButton-root': {
      color: `${textColor}`,
    }
  },
  inputRoot: {
    color: `${textColor}`,
    // This matches the specificity of the default styles at https://github.com/mui-org/material-ui/blob/v4.11.3/packages/material-ui-lab/src/Autocomplete/Autocomplete.js#L90
    '&[class*="MuiOutlinedInput-root"]': {
      // Default left padding is 6px
      padding: '0px 2px'
    },
    "& .MuiOutlinedInput-notchedOutline": {
      borderColor: `${textColor}`
    },
    "&:hover .MuiOutlinedInput-notchedOutline": {
      borderColor: `${textColor}`
    },
    "&.Mui-focused .MuiOutlinedInput-notchedOutline": {
      borderColor: `${textColor}`
    }
  }
}));


const Header = (props) => {
  const localStorageKey = 'myValueInLocalStorage';
  const circuitNamesKey = 'circuitNames';

  const [fileName, setFileName] = useState(
    localStorage.getItem(localStorageKey) || ''
  );
  
  const [isSaveClicked, setIsSaveClicked] = useState(false);

  const [state, setState] = useState(
    localStorage.getItem(localStorageKey) || ''
  );

  const [isLoadClicked, setIsLoadClicked] = useState(false);

  const onChangeCircuit = (event, value, reason) => {
    if (reason !== "clear" || reason !== "reset") {
      isLoadClicked && setIsLoadClicked(false);
      isSaveClicked && setIsSaveClicked(false);
      setFileName(value)
      setState(value)
    }
  }

  const onSaveClicked = () => {
    setIsSaveClicked(true);
    return props.saveCircuit(fileName)
  };
  
  const onLoadClicked = () => {
    setIsLoadClicked(true);
    return props.loadCircuit(state)
  };

  const createOptionsList = () => {
    const circuitNamesList = JSON.parse(localStorage.getItem(circuitNamesKey));
    if (circuitNamesList == null){
      return;
    }
    return circuitNamesList
  }

  useEffect(() => {
    if (!fileName || !fileName.trim() || !isSaveClicked){
      return;
    }

    const circuitNames = JSON.parse(localStorage.getItem(circuitNamesKey)) || [];
    const updatedCircuitNames = circuitNames
      .filter((currFileName) => currFileName !== fileName);
    updatedCircuitNames.push(fileName);
    
    localStorage.setItem(circuitNamesKey, JSON.stringify(updatedCircuitNames));
    localStorage.setItem(localStorageKey, fileName);
  }, [isSaveClicked]);

  return (
    <div style={{ ...headerStyle }}>
      <div style={{ ...groupStyle }}>
        <Autocomplete
          id="circuit-list"
          classes={useStyles()}
          options={createOptionsList()}
          getOptionLabel={(option) => option}
          style={{ width: 200 }}
          onInputChange={onChangeCircuit}
          clearOnBlur={false}
          noOptionsText={'No circuits found'}
          renderInput={
            (params) => <TextField {...params} label="Circuit File" variant="outlined"/>
          }
        />
        <Button text="SAVE CIRCUIT" onClick={onSaveClicked}/>
        <Button text="LOAD CIRCUIT" onClick={onLoadClicked}/>
      </div>
      <div style={{ ...groupStyle }}>
        <Button text="TOGGLE POKE" onClick={() => props.togglePoke()} />
        <Button text="SIMULATE" onClick={() => props.startSimulation()} />
      </div>
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
