import React from 'react';
import PropTypes from 'prop-types';
import { MdClose } from 'react-icons/md';
import './modal.scoped.scss';

const Modal = ({ header, children, closeModal }) => {
  return (
    <>
      <aside className="modal__mask" onClick={closeModal} />
      <section className="modal__wrapper">
        <header className="modal__header">
          <div className="modal__header__wrapper">
            <span className="modal__header__text">{header}</span>
            <MdClose className="post-code__popup__header__btn" onClick={closeModal} />
          </div>
        </header>
        <main className="modal__body">{children}</main>
      </section>
    </>
  );
};

Modal.propTypes = {
  header: PropTypes.oneOfType([PropTypes.string, PropTypes.object]),
  children: PropTypes.node.isRequired,
  closeModal: PropTypes.func.isRequired
};

export default Modal;
