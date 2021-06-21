import React from 'react';
import LinkButton from '@/components/LinkButton';
import './home.scoped.scss';

const Home = () => {
  return (
    <div className="home__container">
      <aside className="home__aside">
        <img src={require('@/assets/images/whistleon-home.png')} alt="whistleon" className="home__img" />
        <img src={require('@/assets/images/whistleon-logo.png')} alt="whistleon" className="home__img--mobile" />
      </aside>
      <section className="home__section">
        <div className="home__greeting">
          <h2 className="home__title">WHISTLE ON</h2>
          <h3 className="home__subtitle">Good Play, Good People</h3>
          <article className="home__description">아름다운 축구 문화를 만들기 위해 앞장서는 휘슬온 입니다.</article>
        </div>
        <div className="home__section__btn-container">
          {[
            {
              btnDir: 'right',
              btnLinkTo: '/login',
              btnText: '로그인'
            },
            {
              btnDir: 'right',
              btnLinkTo: '/signup',
              btnText: '회원가입'
            }
          ].map((buttonAttr, index) => (
            <LinkButton buttonAttr={buttonAttr} key={index} />
          ))}
        </div>
      </section>
    </div>
  );
};

export default Home;
