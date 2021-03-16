import React, { useState, useEffect } from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import useMediaQuery from '@material-ui/core/useMediaQuery';
import { useTheme, makeStyles } from '@material-ui/core/styles';

import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';


/**
 * A template for the attribute tables for devices in the application
 * @author:jimmyoriyomi
 */

// set styles(css) for the table and button
const useStyles = makeStyles({
  table: {
    minWidth: '100%',
  },
  button: {
    width: '95%',
    marginRight: '10%',
    marginTop: '5px',
    textTransform: 'none',
    fontSize: '11px',
    height: '70px'
  },
});

function createData(name, configu) {
  return { name, configu };
}

/*
 - create a dictionary of attribute name to attribute value
  - key is attribute name in lowercase (!important case sensitive) e.g. and, or, not
    - we use this to make this component work for each logic gate and flip-flop
  - value is a dictionary of features and their corresponding output
    - e.g. 'facing' is the feature and 'EAST' is the corresponding value for 'facing'
*/
const attributesData = {
  'and': {
    'no_of_inputs': 2,
    'no_of_outputs': 1,
    'name_of_device': 'AND Gate'
  },
  'three input and': {
    'no_of_inputs': 3,
    'no_of_outputs': 1,
    'name_of_device': 'AND Gate'
  },
  'three input nor': {
    'no_of_inputs': 3,
    'no_of_outputs': 1,
    'name_of_device': 'NOR Gate'
  },
  'three input or': {
    'no_of_inputs': 3,
    'no_of_outputs': 1,
    'name_of_device': 'OR Gate'
  },
  'three input nand': {
    'no_of_inputs': 3,
    'no_of_outputs': 1,
    'name_of_device': 'NAND Gate'
  },
  'nand': {
    'no_of_inputs': 2,
    'no_of_outputs': 1,
    'name_of_device': 'NAND Gate'
  },
  'not': {
    
    'no_of_inputs': 1,
    'no_of_outputs': 1,
    'name_of_device': 'NOT Gate'
  },
  'or': {
    'no_of_inputs': 2,
    'no_of_outputs': 1,
    'name_of_device': 'OR Gate'
  },
  'nor': {
    'no_of_inputs': 2,
    'no_of_outputs': 1,
    'name_of_device': 'NOR Gate'
  },
  'xor': {
    'no_of_inputs': 2,
    'no_of_outputs': 1,
    'name_of_device': 'XOR Gate'
  },
  'xnor': {
    'no_of_inputs': 2,
    'no_of_outputs': 1,
    'name_of_device': 'XNOR Gate'
  },
  'd flip-flop': {
    'no_of_inputs': 2,
    'no_of_outputs': 2,
    'name_of_device': 'D FLIP-FLOP'
  }
};

export default function AttributeTable(props) {
  // Popup config
  const classes = useStyles();
  // using useState here makes the component to be re-rendered when the open 
  // variable is updated. This behaviours is what ensures the popup is opened and/or closed
  const [open, setOpen] = useState(false);
  const theme = useTheme();
  const fullScreen = useMediaQuery(theme.breakpoints.down('sm'));

  const closeDialog = props.closeDialog;
  useEffect(() => {
    setOpen(props.openDialog);
  }, [props.openDialog]);

  // set open to false when handleClose is called
  const handleClose = () => {
    setOpen(false);
    closeDialog();
  };

  /*
    We use the attributesData to get the corresponding value for a given
    logica gate or flip-flop. Since the name of the logic gate is passed in 
    via the props.name we convert the name to lowercase and access it using the format
    dictionary[key] in this case our dictionary is the atttributesData constant and the 
    key is the name of the logic gate or flip flop in lowercase. 
  */
  const attributeData = attributesData[props.name.toLowerCase()]; 

  // create table rows using the attributeData const above which has the feature information
  // for the corresponding logic gate or flip-flop
  const rows = [
    createData('No of Inputs', attributeData['no_of_inputs']),
    createData('No of Outputs', attributeData['no_of_outputs']),
    createData('Name of Device', attributeData['name_of_device']),
  ];

  const header = `${props.name} Attribute Table`;
  
  return (
    <div>
      <Dialog
        fullScreen={fullScreen}
        open={open}
        onClose={handleClose}
        aria-labelledby="responsive-dialog-title"
      >
        <DialogTitle id="responsive-dialog-title">{header}</DialogTitle>
        <DialogContent>
          <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="simple table">
              <TableHead>
                <TableRow>
                  <TableCell>DEVICE INFORMATION</TableCell>
                  <TableCell align="right">CONFIGURATION</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow key={row.name}>
                    <TableCell component="th" scope="row">
                      {row.name}
                    </TableCell>
                    <TableCell align="right">{row.configu}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary" autoFocus>
            Close
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );

}