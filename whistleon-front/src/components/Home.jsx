import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <div>
      <button>
        <Link to="/login">로그인</Link>
      </button>
      <button>
        <Link to="/signup">회원가입</Link>
      </button>
    </div>
  );
};

export default Home;