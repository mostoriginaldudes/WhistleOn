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
        <h2 className="home__title">Whistle On</h2>
        <h3 className="home__subtitle">Good Play, Good People</h3>
        <article className="home__description">
          축구 경기 매칭 플랫폼, 휘슬온 입니다.
        </article>
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