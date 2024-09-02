import React, { useState, useEffect } from 'react';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import axios from 'axios';

function MyCalendar() {
    const [events, setEvents] = useState([]);

    useEffect(() => {
        fetchEvents();
    }, []);

    const fetchEvents = () => {
        axios.get('/api/events')
            .then(response => setEvents(response.data))
            .catch(error => console.error('Error fetching events:', error));
    };

    const handleDateClick = (arg) => {
        const inputTitle = prompt(arg.dateStr,'일정을 입력하세요');
        if (inputTitle) {
            const newEvent = {
                title: inputTitle,
                start: arg.dateStr + 'T00:00:00', // 시간 정보를 추가
                end: arg.dateStr + 'T23:59:59'   // 종료 시간 정보를 추가 (하루 종일 이벤트일 경우)
            };

            console.log('Sending new event:', newEvent); // 요청 본문 로그 확인

            axios.post('/api/events/add', newEvent, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(() => fetchEvents())
            .catch(error => {
                console.error('Error adding event:', error);
                // 오류 메시지를 콘솔에 기록합니다.
                console.error('Error details:', error.response ? error.response.data : error.message);
            });
        }
    };

    const handleEventClick = (arg) => {
        if (window.confirm(`해당 포스팅을 삭제하시겠습니까? '${arg.event.title}'?`)) {
            axios.delete(`/api/events/delete/${arg.event.id}`)
                .then(() => fetchEvents())
                .catch(error => {
                    console.error('Error deleting event:', error);
                    console.error('Error details:', error.response ? error.response.data : error.message);
                });
        }
    };

    return (
        <FullCalendar
            plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin]}
            initialView="dayGridMonth"
            events={events.map(event => ({
                title: event.title,
                id: event.id,
                start: event.start,
                end: event.end
            }))}
            dateClick={handleDateClick}
            eventClick={handleEventClick}
        />
    );
}

export default MyCalendar;
