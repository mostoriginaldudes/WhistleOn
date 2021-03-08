import React from 'react';
import LinkButton from './common/LinkButton.jsx';
import '@/style/home.scss';

const Home = ({ history }) => {
  const btnProps = [
    {
      dir: 'right',
      to: '/login',
      text: '로그인'
    },
    {
      dir: 'right',
      to: '/signup',
      text: '회원가입'
    },
  ];

  return (
    <div className="home__container">
      <aside className="home__aside">
        <img src={require('@/assets/images/whistleon-home.png')} alt="whistleon" className="home__img" />
        <img src={require('@/assets/images/whistleon-logo.png')} alt="whistleon" className="home__img--mobile" />
      </aside>
      <section className="home__section">
        <div className="home__greeting">
          <h2 className="home__title">Whistle On</h2>
          <h3 className="home__subtitle">Good Play, Good People</h3>
          <article className="home__description">
            아름다운 축구 문화를 만들기 위해 앞장서는 휘슬온 입니다.
          </article>
        </div>
        <div className="home__section__btn-container">
          {btnProps.map(({dir, to, text}, index) => 
            <LinkButton 
              dir={dir}
              to={to}
              text={text}
              key={index}
            />)
          }
        </div>
      </section>
    </div>
  );
};

export default Home;