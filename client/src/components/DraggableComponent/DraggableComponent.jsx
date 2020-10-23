import React from 'react';
import { connect } from 'react-redux';
import { Group } from 'react-konva';
import { cellSize } from '../../util/style';
import { showComponentShadow, setComponentShadowType } from '../../store/component/actions';
import { buttonPress } from '../../store/command/actions';

/**
 * Any component that a user can place will be wrapped by this.
 * Enables a component to be moved
 * @author:kyhorne
 */
class DraggableComponent extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      x: props.x,
      y: props.y,
    };
  }

  render() {
    const { x, y } = this.state;
    const { props } = this;
    return (
      <Group>
        {React.Children.map(props.children, (child) =>
          React.cloneElement(child, {
            x,
            y,
            draggable: true,
            onDragEnd: (e) => {
              const { attrs } = e.target;
              this.setState({
                x: Math.round(attrs.x / cellSize) * cellSize,
                y: Math.round(attrs.y / cellSize) * cellSize,
              });
            },
            onDragMove: (e) => {
              const { attrs } = e.target;
              props.showComponentShadow(
                Math.round(attrs.x / cellSize) * cellSize,
                Math.round(attrs.y / cellSize) * cellSize,
              );
            },
            onMouseDown: () => {
              console.log(this.props.commandReducer.isPoke);
              if (this.props.commandReducer.isPoke) {
                props.buttonPress();
              } else {
                props.setComponentShadowType(props.type);
              }
            },
          }),
        )}
      </Group>
    );
  }
}

const mapStateToProps = (state) => ({
  componentReducer: state.componentReducer,
  commandReducer: state.commandReducer,
});

const mapDispatchToProps = {
  showComponentShadow,
  setComponentShadowType,
  buttonPress,
};

export default connect(mapStateToProps, mapDispatchToProps)(DraggableComponent);
