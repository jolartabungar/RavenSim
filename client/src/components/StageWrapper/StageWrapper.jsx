import React from 'react';
import { Stage } from 'react-konva';
import { ReactReduxContext, Provider } from 'react-redux';

/**
 * Creates a Stage component for konva shapes using the size of the parent component.
 * @author:kyhorne
 */
class StageWrapper extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      stageWidth: undefined,
      stageHeight: undefined,
    };

    this.ref = React.createRef();
  }

  componentDidMount() {
    this.updateSize();
    window.addEventListener('resize', this.updateSize);
  }

  componentWillUnmount() {
    window.removeEventListener('resize', this.updateSize);
  }

  updateSize() {
    if (this.ref === undefined) {
      return;
    }
    const { offsetWidth, offsetHeight } = this.ref.current;
    this.setState({
      stageWidth: offsetWidth,
      stageHeight: offsetHeight,
    });
  }

  render() {
    const { stageWidth, stageHeight } = this.state;
    const {
      children,
      style,
      onMouseUp,
      onMouseMove,
    } = this.props;
    return (
      <div style={{ ...style }} ref={this.ref}>
        <ReactReduxContext.Consumer>
          {({ store }) => (
            <Stage
              onMouseUp={onMouseUp}
              onMouseMove={onMouseMove}
              width={stageWidth}
              height={stageHeight}
            >
              <Provider store={store}>{children}</Provider>
            </Stage>
          )}
        </ReactReduxContext.Consumer>
      </div>
    );
  }
}

export default StageWrapper;
